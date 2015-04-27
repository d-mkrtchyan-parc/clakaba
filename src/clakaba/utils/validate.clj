(ns clakaba.utils.validate)

(defn conj-val[from desc]
  (if (and 
        (:class from)
        (:class desc))
      (throw "Invalid field in DESC: class\n You can't use css class as property of descriptor it may cause of confilcts")
      (conj from desc)))
