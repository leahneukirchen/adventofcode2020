(ns day18
  (:require [instaparse.core :as insta])
  (:require [clojure.core.match :refer [match]]))

(def WS
  (insta/parser "WS = #'\\s+'"))

(def transform-options
  {:IntLiteral read-string})

(def parser1
  (insta/parser
    "AddExpr = AddExpr '+' Paren | AddExpr '*' Paren | Paren
     Paren = '(' AddExpr ')' | IntLiteral
     IntLiteral = #'[0-9]+'"
    :auto-whitespace WS))

(def parser2
  (insta/parser
    "MultExpr = MultExpr '*' AddExpr | AddExpr
     AddExpr = AddExpr '+' Paren | Paren
     Paren = '(' MultExpr ')' | IntLiteral
     IntLiteral = #'[0-9]+'"
    :auto-whitespace WS))

(defn eval-expr [expr]
  (match expr
         [:MultExpr e1 "*" e2] (* (eval-expr e1) (eval-expr e2))
         [:AddExpr e1 "+" e2] (+ (eval-expr e1) (eval-expr e2))
         [:AddExpr e1 "*" e2] (* (eval-expr e1) (eval-expr e2))
         [:MultExpr e1] (eval-expr e1)
         [:AddExpr e1] (eval-expr e1)
         [:Paren e1] (eval-expr e1)
         [:Paren "(" e1 ")"] (eval-expr e1)
         :else expr))

(defn eval1 [input]
  (->> (parser1 input)
       (insta/transform transform-options)
       eval-expr))

(->> (slurp "day18")
     clojure.string/split-lines
     (map eval1)
     (apply +))
;; => 3159145843816

(defn eval2 [input]
  (->> (parser2 input)
       (insta/transform transform-options)
       eval-expr))

(->> (slurp "day18")
     clojure.string/split-lines
     (map eval2)
     (apply +))
;; => 55699621957369
