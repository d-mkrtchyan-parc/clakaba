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
]))

(defn get-threads[id]
	(vec [
		{:id 1 :domain id :posts posts}
		{:id 2 :domain id :posts posts}
	]))