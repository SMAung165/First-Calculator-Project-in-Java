# 🧮 Java Calculator

A console-based calculator built with Java, designed as a first real-world OOP project. What started as a simple arithmetic tool evolved into a well-structured application covering core Java concepts — inheritance, polymorphism, composition, and user input handling.

---

## ✨ Features

- **Normal Calculator** — addition, subtraction, multiplication, division
- **Scientific Calculator** — power (`^`) and square root (`sqrt`)
- **Expression-based input** — type `5 + 3` instead of selecting operations from a menu
- **Calculation history** — review all past operations in a session
- **Input validation** — handles invalid inputs, division by zero, and edge cases gracefully
- **Calculator switching** — switch between Normal and Scientific mid-session

---

## 🛠️ Tech Stack

| | |
|---|---|
| Language | Java 17+ |
| IDE | IntelliJ IDEA |
| Build | IntelliJ Native |

---

## 🚀 How To Run

1. Clone the repo
```bash
git clone https://github.com/SMAung165/Calculator.git
```
2. Open in IntelliJ IDEA
3. Run `Main.java`

---

## 💡 Usage

```
Select Calculator Type (1) -> (Normal), (2) -> (Scientific), (q) -> (Quit)
> 1
Normal Calculator Selected
Enter expression: 5 + 3
5.00 + 3.00 = 8.00
Enter expression: 10 / 2
10.00 / 2.00 = 5.00
Enter expression: h
History of Normal Calculator:
------------------------------
5.00 + 3.00 = 8.00
10.00 / 2.00 = 5.00
------------------------------
```

---

## 🧠 Concepts Practiced

- OOP — classes, encapsulation, inheritance, polymorphism
- Composition — `Calculator` HAS a `History`
- Interfaces — `ScientificCalculations`
- Input parsing — expression-based input with regex-like string manipulation
- Exception handling — division by zero, invalid inputs
- Single source of truth — history as the only data store

---

## 📁 Project Structure

```
src/
├── Main.java
├── Calculator.java
├── ScientificCalculator.java
├── History.java
└── contracts/
    └── ScientificCalculations.java
```

---

## 🗺️ What's Next

This project was a stepping stone. Next up — KanjiFlash, a Kanji flashcard quiz app, and eventually a full Chat Application built with Java Sockets.

---

> Built from scratch as a learning project — no tutorials, no copy-paste. Just thinking, breaking things, and fixing them. 💪
