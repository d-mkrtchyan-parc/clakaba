(ns blocker
  (:require [utils :as utils]
						[enfocus.core :as ef]
            [enfocus.events :as events]
						[ajax.core :refer [GET POST]]
            [tailrecursion.javelin :refer [cell]])
	
  (:require-macros  [enfocus.macros :as em]
                    [tailrecursion.javelin :refer [cell=]]))


(defn start[]
	(let [cnt (ef/select "#main-content")
				menu (ef/select "#menu")]
		
		(ef/at "#menu a" (events/listen :click 
			(fn[event] (do 
				 (.preventDefault event) 
				 (GET "/boards/mu" 
							{	:handler #(.log js/console %)
              	:error-handler #(.log js/console %) })))))))

(defn doc-ready-handler []
  (let[ ready-state (. js/document -readyState)]
    (if (= "complete" ready-state)
      (do (start)))))

(aset  js/document "onreadystatechange" doc-ready-handler )