# Hub

## Description
The easiest plugin on /hub that I fucked my ass hard for ~2 hours,
and the reason for that is POM.XML

## Features
* **Hub**. Added /hub.

## Download
1. Download plugin with **[GitHub Releases](https://github.com/Mellend/Hub/releases)**.
2. Put the file `.jar` in folder `plugins` your server **Velocity**.
3. Restart your proxy

## Usage
Run the `/hub` command, for returns into hub.

## License
This project is **Licensed under the** `GPL-3.0 License`. See the [**LICENSE**](LICENSE) file for details.

## Build
1. Clone the repository to yourself using **git using the command**:
   `git clone https://github.com/Mellend/Hub <place to copy>`.
2. Open the file using your IDE (In my case **Intellij Idea**).
    * File -> Project Structure -> Artifacts -> “+” -> Jar -> From modules with dependencies.
    * Select in `Jar files from libraries` - copy to the output directory and link via manifest.
    * Click on “**OK**”.
3. From the top menu, select (Same place as **File**).
    * Build -> Build Artifacts -> mellend-main-paper:jar -> Build.
    * Wait a couple of seconds.
4. Navigate to `/out/artifacts/hub_jar`, find **hub.jar** there.
5. That's it, you now have a self-built `.jar`.