package com.macroid.layouts

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.{ActionBarActivity, ActionBarDrawerToggle}
import android.view.{MenuItem, Menu, View}
import android.widget.TextView
import com.fortysevendeg.macroid.extras.DrawerLayoutTweaks._
import com.fortysevendeg.macroid.extras.FragmentExtras._
import com.fortysevendeg.macroid.extras.ToolbarTweaks._
import com.macroid.menu.{MenuSection, MenuFragment}


// import macroid stuff
import macroid.FullDsl._
import macroid._

class MainActivity extends ActionBarActivity with Styles with Layout with Contexts[FragmentActivity] with IdGeneration{
  var cap = slot[TextView]

  var actionBarDrawerToggle: Option[ActionBarDrawerToggle] = None

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)

    setContentView(layout)

    toolBar map setSupportActionBar

    getSupportActionBar.setDisplayHomeAsUpEnabled(true)
    getSupportActionBar.setHomeButtonEnabled(true)


    drawerLayout map { drawerLayout =>
      val drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openMenu, R.string.clodeMenu) {
        override def onDrawerClosed(drawerView: View): Unit = {
          super.onDrawerClosed(drawerView)
          invalidateOptionsMenu()
          findFragmentById[MenuFragment](Id.menuFragment) map (_.showMainMenu)
        }

        override def onDrawerOpened(drawerView: View): Unit = {
          super.onDrawerOpened(drawerView)
          invalidateOptionsMenu()
        }
      }
      actionBarDrawerToggle = Some(drawerToggle)
      drawerLayout.setDrawerListener(drawerToggle)
    }
    if (savedInstanceState == null) {
      runUi(
        replaceFragment(
          builder = f[MenuFragment],
          id = Id.menuFragment,
          tag = Some(Tag.menuFragment)))
    }
  }
  override def onNewIntent(intent: Intent): Unit = {
    super.onNewIntent(intent)
    setIntent(intent)
  }

  override def onActivityResult(requestCode: Int, resultCode: Int, data: Intent): Unit =
    super.onActivityResult(requestCode, resultCode, data)

  override def onCreateOptionsMenu(menu: Menu): Boolean = super.onCreateOptionsMenu(menu)

  override def onPostCreate(savedInstanceState: Bundle): Unit = {
    super.onPostCreate(savedInstanceState)
    actionBarDrawerToggle map (_.syncState)

  }

  override def onConfigurationChanged(newConfig: Configuration): Unit = {
    super.onConfigurationChanged(newConfig)
    actionBarDrawerToggle map (_.onConfigurationChanged(newConfig))
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    if (actionBarDrawerToggle.isDefined && actionBarDrawerToggle.get.onOptionsItemSelected(item)) true
    else super.onOptionsItemSelected(item)
  }

}
