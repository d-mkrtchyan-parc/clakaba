(ns clakaba.routes.home
  (:require [compojure.core :refer :all]
            [clakaba.views.layout :as layout]))

(defn index [i]
  (layout/page (layout/index i)))

(defn board [id]
	(layout/get-board-content id))

(defroutes home-static
  (GET "/" [] (index nil))
	(GET "/boards" [] (index 0))
	(GET "/boards/:id" {{id :id} :params} (board id)))
