package com.macroid.layouts

import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.{FrameLayout, LinearLayout}
import com.fortysevendeg.macroid.extras.DrawerLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid.{AppContext, Tweak}

import scala.language.postfixOps

/**
 * Created by pranjut on 10/4/15.
 */
trait Styles {
  val drawerStyle: Tweak[DrawerLayout] = vMatchParent

  def drawerLayoutStyle(implicit appContext: AppContext): Tweak[FrameLayout] =
    lp[FrameLayout](resGetDimensionPixelSize(R.dimen.width_drawer), MATCH_PARENT) +
      dlLayoutGravity(Gravity.START)

  val contentStyle: Tweak[LinearLayout] =
    vMatchParent +
      llVertical

  val fragmentContentStyle: Tweak[FrameLayout] =
    vMatchParent
}
