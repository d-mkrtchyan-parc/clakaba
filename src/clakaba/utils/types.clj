(ns clakaba.utils.types)

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))