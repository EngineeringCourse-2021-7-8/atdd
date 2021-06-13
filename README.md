# Setup environment

* Install Intellij Community
* Install Google Chrome
* Install docker and docker compose
  * For Windows, please follow the installation guide at: https://docs.docker.com/docker-for-windows/install/
  * Please make sure the followings are installed before installing docker for windows
    * WSL 2 https://docs.microsoft.com/en-us/windows/wsl/install-win10
    * Hyper-V backend and Windows containers should be enabled in `Windows feature`

* Please add the following host names into your hosts file. The ip address should be your Wifi ip address

```ini
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
```

* Start environment for running tests

```shell
cd env/compose/dc_pc
docker-compose up
```

## Use correct chrome driver base on your chrome version
* Find your chrome version
* Update the chrome driver version in `atdd/backend/build.gradle` to match your chrome version
* You can find the chrome driver version number in `atdd/backend/webdriver_download_config.json`, for example if your chrome version is 90.xx, then you can choose `90.0.4430.24`.

# Run all tests

## For Linux and Mac

```shell
./gradlew cucumber
```

## For Windows

```shell
gradlew.bat cucumber
```

You may encounter character encoding issue in terminal. If so, please try to fix it by following this
link https://akr.am/blog/posts/using-utf-8-in-the-windows-terminal

# Run the application

## For Linux and Mac

```shell
./gradlew bootRun
```

## For Windows

```shell
gradlew.bat bootRun
```

# Open project in IDE
* Open the project root folder with Intellij Community, then later you can write/run test in Intellij.

