package autowire.electron

import _root_.electron.ipc

trait ElectronIpcWireClient {
  protected val ipcRenderer: ipc.IpcRenderer
}
