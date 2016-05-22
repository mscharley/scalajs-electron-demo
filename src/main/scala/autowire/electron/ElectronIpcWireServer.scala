package autowire.electron

import nodejs.raw

trait ElectronIpcWireServer {
  protected val ipcMain: raw.EventEmitter
}
