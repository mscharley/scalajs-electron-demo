package demo

import nodejs.Require
import electron._
import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global, literal => JSObject}
import scala.scalajs.js.annotation.JSExport

@JSExport("Demo.App")
class App(dirName: String, require: Require) extends ElectronApp(require) with js.JSApp {
  // Keep a global reference of the window object, if you don't, the window will
  // be closed automatically when the JavaScript object is garbage collected.
  var mainWindow: Option[BrowserWindow] = None
  val console = global.console
  val api = new ApiServer(electron.ipcMain)

  def createWindow() = {
    // Create the browser window.
    mainWindow = Some(BrowserWindow(JSObject(width = 800, height = 600)))
    mainWindow foreach { window =>
      // and load the index.html of the app.
      window.loadURL("file://" + dirName + "/index.html")

      // Open the DevTools.
      window.webContents.openDevTools()

      // Emitted when the window is closed.
      // Dereference the window object, usually you would store windows
      // in an array if your app supports multi windows, this is the time
      // when you should delete the corresponding element.
      window.on("closed"){ () => mainWindow = None }
    }
  }

  def main() = {
    console.log("Starting scalajs-electron-demo...")

    // This method will be called when Electron has finished initialization and is ready to create browser windows.
    electronApp onceReady createWindow _

    process.platform.asInstanceOf[String] match {
      case "darwin" =>
        // On OS X it is common for applications and their menu bar to stay active until the user quits explicitly
        // with Cmd + Q and re-create a window in the app when the dock icon is clicked and there are no other
        // windows open.
        electronApp onActivate { () =>
          if (mainWindow.isEmpty) {
            createWindow()
          }
        }

      case _ =>
        // For everyone else, we just die immediately.
        electronApp onWindowAllClosed { () => electronApp.quit() }
    }
  }
}
