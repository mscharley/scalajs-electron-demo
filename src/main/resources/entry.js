'use strict';

require('source-map-support').install();
(function() {
    var appName = 'scalajs-electron-quick-start';
    try {
        require('../' + appName + '-opt');
    }
    catch (e) {
        require('../' + appName + '-fastopt');
    }

    console.log("Starting scalajs-electron-quick-start...");
    ElectronQuickStart.App(__dirname, require('electron')).main();
})();
