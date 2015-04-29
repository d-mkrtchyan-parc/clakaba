(ns clakaba.dsl.database
  (:require 
    [monger.core :as mg]
    [clakaba.variables :refer [db-conf]])
  (:import [com.mongodb MongoOptions ServerAddress]))

(defn rand-img[]
	(str "http://placehold.it/" (rand-int 200) "x" 200 ".png"))

(defn get-boards []
	(vec [
		{:url "mu" :name "Music"}
		{:url "s" :name "Software"}
		{:url "dev" :name "Development"}
    {:url "vg" :name "Video Games"}
		{:url "b" :name "Random"} ]))

(def posts (vec [
	{:id 1 :email "test@test.com" :content "Allahu akbar" :image {:src (rand-img)}}
	{:id 2 :email "zhest@test.com" :content "Kozanoid" :image {:src (rand-img)}}
	{:id 3 :email "zhest@test.com" :content "<h3>Do da shit!</h3>" :image {:src (rand-img)}}
	{:id 4 :email "zhest@test.com" :content ">>2 GTFO!"}
]))

(defn get-threads[id]
	(vec [
		{:id 1 :domain "b" :posts posts}
		{:id 2 :domain "mu" :posts posts}
    {:id 3 :domain "mu" :posts posts}
    {:id 4 :domain "vg" :posts posts}
	]))