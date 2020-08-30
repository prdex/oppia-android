package org.oppia.testing.screenshots

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileOutputStream

const val OUTPUT_FOLDER_NAME = "OppiaScreenshots"

/**A class that is used to take screenshots from some activity.*/
class ScreenshotManager {

  /**
   * A function that takes a screenshot of a given activity and stores it in the
   * application's files.
   * @param activity The target activity to take a screenshot of.
   * @return a bitmap representing the screenshot of the activity.
   */
  fun takeScreenshot(activity: Activity): Bitmap {
    val view = activity.window.decorView.rootView
    val bitmap = Bitmap.createBitmap(
      view.width,
      view.height, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    val fileName = "${activity::class.java.name}.png"
    val outputFolder =
      File("${Environment.getExternalStorageDirectory().path}/$OUTPUT_FOLDER_NAME")
    val imageFile =
      File(outputFolder, fileName)
    val outputStream = FileOutputStream(imageFile)
    val quality = 100
    bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
    outputStream.flush()
    outputStream.close()
    return bitmap
  }

  /**
   * A function that takes a screenshot of a given view and stores it in the
   * application's files.
   * @param view The target view to take a screenshot of.
   * @return a bitmap representing the screenshot of the activity.
   */
  fun takeScreenshot(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(
      view.width,
      view.height, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    val viewName = view.context.resources.getResourceEntryName(view.id)
    val fileName = "${view.context::class.java.name}_$viewName.png"
    val outputFolder =
      File("${Environment.getExternalStorageDirectory().path}/$OUTPUT_FOLDER_NAME")
    val imageFile =
      File(outputFolder, fileName)
    val outputStream = FileOutputStream(imageFile)
    val quality = 100
    bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
    outputStream.flush()
    outputStream.close()
    return bitmap
  }
}
