# QR Generator &middot; ![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)
Generate QR codes using this app.
## Installation

* Download [apk](https://maxsiomin.dev/apps/qr_generator/qr_generator.apk)
* Clone the repo using git:
```bash
git clone https://github.com/MaxSiominDev/QR-Generator.git
```
## Requirements

Any Android 8.0+ device where Google Services are available

## For developers
This app uses navigation component to provide navigation. 
App receives updates from my server using retrofit. 
For generating QR codes I used zxing embedded.
This app saves history with Room database.
For logging I used Timber.
Auth and analytics are provided by Firebase.
DI implemented with Hilt.

## License 
QR generator is [MIT licensed](./LICENSE).
