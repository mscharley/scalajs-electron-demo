# electron-quick-start (Scala.js port)

**Clone and run for a quick way to see an Electron in action.**

This is a minimal Electron application based on the [Quick Start Guide](http://electron.atom.io/docs/latest/tutorial/quick-start) within the Electron documentation.

A basic Electron application needs just these files:

- `index.html` - A web page to render.
- `main.js` - Starts the app and creates a browser window to render HTML.
- `package.json` - Points to the app's main file and lists its details and dependencies.

You can learn more about each of these components within the [Quick Start Guide](http://electron.atom.io/docs/latest/tutorial/quick-start).

## To use

To clone and run this repository you'll need [Git](https://git-scm.com) and [Node.js](https://nodejs.org/en/download/) (which comes with [npm](http://npmjs.com)) as well as [SBT](http://www.scala-sbt.org/) installed on your computer. From your command line:

```bash
# Clone this repository
git clone https://github.com/atom/electron-quick-start
# Go into the repository
cd electron-quick-start
# Install dependencies and run the app
npm install && npm start
```

Learn more about Electron and its API in the [documentation](http://electron.atom.io/docs/latest).

## Scala support

The `main.js` in the documentation mostly translates to `App.scala` in this repository. There is a very small amount
of glue in `entry.js` which is what Electron actually runs that simply loads and delegates to the compiled Scala.js
code.

#### License [MIT](LICENSE.md)
