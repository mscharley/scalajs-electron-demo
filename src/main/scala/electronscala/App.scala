package electronscala

import electron.BrowserWindow

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport("electronscala.App")
class App(dirName: String)(implicit electron: js.Dynamic) {
  lazy val process = js.Dynamic.global.process

  // Keep a global reference of the window object, if you don't, the window will
  // be closed automatically when the JavaScript object is garbage collected.
  var mainWindow: Option[js.Dynamic] = None

  def createWindow(): Unit = {
    println("Attempted to open a window.")
    // Create the browser window.
    mainWindow = Some(BrowserWindow(js.Dynamic.literal(width = 800, height = 600)))
    mainWindow foreach { window =>
      // and load the index.html of the app.
      window.loadURL("file://" + dirName + "/index.html")

      // Open the DevTools.
      window.webContents.openDevTools()

      // Emitted when the window is closed.
      // Dereference the window object, usually you would store windows
      // in an array if your app supports multi windows, this is the time
      // when you should delete the corresponding element.
      window.on("closed", () => mainWindow = None);
    }
  }

  @JSExport
  def main() = {
    // Module to control application life.
    val app = electron.app

    // This method will be called when Electron has finished
    // initialization and is ready to create browser windows.
    app.on("ready", () => createWindow())

    // Quit when all windows are closed.
    app.on("window-all-closed", () => process.platform.asInstanceOf[String] match {
      // On OS X it is common for applications and their menu bar
      // to stay active until the user quits explicitly with Cmd + Q
      case "darwin" =>
      // For everyone else, we just die immediately.
      case _ =>
        app.quit()
    })

    app.on("activate", () => mainWindow match {
      case None =>
        // On OS X it's common to re-create a window in the app when the
        // dock icon is clicked and there are no other windows open.
        createWindow()

      case Some(_) =>
    })

  }
}
