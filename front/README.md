# JobDevs (front)

TCC project

## Install the dependencies

```bash
yarn
# or
npm install
```

### Start the app in development mode (hot-code reloading, error reporting, etc.)

```bash
quasar dev
```

### Lint the files

```bash
yarn lint
# or
npm run lint
```

### Format the files

```bash
yarn format
# or
npm run format
```

### Build the app for production

```bash
quasar build
```

### Customize the configuration

See [Configuring quasar.config.js](https://v2.quasar.dev/quasar-cli-vite/quasar-config-js).

## Backend

docker start mariadb
docker exec -it 4ba406b33ab26f2085153d6f93d7bc046858fef5b7e5fbb4964cf4dcc0241656 bash
mariadb -uroot -p132567
drop database jobdev;
create database jobdev;
mvn spring-boot:run
