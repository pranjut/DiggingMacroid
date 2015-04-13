package com.macroid.layouts

import android.support.v4.widget.DrawerLayout
import android.widget.{TextView, LinearLayout, FrameLayout}
import macroid.FullDsl._
import macroid.{ActivityContext, AppContext, IdGeneration}

/**
 * Created by pranjut on 10/4/15.
 */
trait Layout
  extends ToolbarLayout
  with IdGeneration
  with Styles {

  var drawerLayout = slot[DrawerLayout]

  var fragmentMenu = slot[FrameLayout]

  var fragmentContent = slot[FrameLayout]

  def layout(implicit appContext: AppContext, context: ActivityContext) = {
    getUi(
      l[DrawerLayout](
        l[LinearLayout](

          toolBarLayout(),
          w[TextView] <~ text("Hey I think i work"),
          l[FrameLayout]() <~ wire(fragmentContent) <~ id(Id.mainFragment) <~ fragmentContentStyle
        ) <~ contentStyle,
        l[FrameLayout]() <~ wire(fragmentMenu) <~ id(Id.menuFragment) <~ drawerLayoutStyle
      ) <~ wire(drawerLayout) <~ drawerStyle
    )
  }

}
