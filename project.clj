(defproject clakaba "0.0.1"
  :description "Imageboard engine on Clojure"
  :url "https://github.com/d-mkrtchyan-parc/clakaba"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [com.cemerick/friend "0.2.1"]
                 [com.novemberain/monger "2.0.1"]                 

                 ;front-end
                 [org.clojure/clojurescript "0.0-2505"]
                 [enfocus "2.1.1"]
								 [cljs-ajax "0.3.11"]
                 [org.clojars.franks42/cljs-uuid-utils "0.1.3"]]
  :plugins [[lein-ring "0.8.12"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]
  :cljsbuild {:builds [
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
  :ring {:handler clakaba.handler/app
         :init clakaba.handler/init
         :destroy clakaba.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? true, :stacktraces? false, :auto-reload? true}}
   :dev
   {:dependencies [[ring-mock "0.1.5"]
                   [ring/ring-devel "1.3.1"]
                   [lein-light-nrepl "0.1.0"]]}})
