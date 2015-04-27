(ns clakaba.dsl.database)

(defn get-boards []
	(vec [
		{:url "mu" :name "Music"}
		{:url "s" :name "Software"}
		{:url "dev" :name "Development"}
		{:url "b" :name "Random"} ]))