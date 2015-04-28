(ns clakaba
  (:require [utils :as utils]
    [enfocus.core :as ef]
    [enfocus.events :as events]
    [ajax.core :refer [GET POST]])

  (:require-macros  
    [enfocus.macros :as em]))


(defn start[]
  (let [actors (ef/select "[js]")
        menu (ef/select "#menu")]
))


(defn doc-ready-handler []
  (let[ ready-state (. js/document -readyState)]
      (if (= "complete" ready-state)
          (do (start)))))

(aset  js/document "onreadystatechange" doc-ready-handler )