(ns clakaba.dsl.database)

(defn get-boards []
	(vec [
		{:url "mu" :name "Music"}
		{:url "s" :name "Software"}
		{:url "dev" :name "Development"}
		{:url "b" :name "Random"} ]))

(def posts (vec [
	{:id 1 :email "test@test.com" :content "Allahu akbar" :image {:src "http://placehold.it/200x200.png"}}
	{:id 2 :email "zhest@test.com" :content "Kozanoid" :image {:src "http://placehold.it/200x200.png"}}
	{:id 3 :email "zhest@test.com" :content "<h3>Do da shit!</h3>" :image {:src "http://placehold.it/200x200.png"}}
	{:id 4 :email "zhest@test.com" :content ">>2 GTFO!"}
]))

(defn get-threads[id]
	(vec [
		{:id 1 :domain id :posts posts}
		{:id 2 :domain id :posts posts}
	]))