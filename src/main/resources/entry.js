'use strict';

require('source-map-support').install();
(function() {
    var appName = 'electron-scala';
    try {
        require('../' + appName + '-opt');
    }
    catch (e) {
        require('../' + appName + '-fastopt');
    }

    console.log("Starting electron-scala...");
    electronscala.App(__dirname, require('electron')).main();
})();
