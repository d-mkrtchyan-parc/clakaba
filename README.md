# CLAKABA
### Imageboard engine on Clojure

### Start
Start Ring server
```
lein ring server
```
Compile ClojureScript to JavaScript
```
lein cljsbuild once
```
Compile LESS to CSS
```
gulp less
```

### Development
Watch for changes in `.cljs` and `.less` files and run adequate tasks from lein and gulp:
```
lein cljsbuild auto
gulp
```
