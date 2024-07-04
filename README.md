# Typing Speed Test

A Java-based application to measure typing speed and accuracy. The Typing Speed Test provides two modes: typing the full given text and typing within a one-minute timer. This project is designed to help users improve their typing skills by providing immediate feedback on typing speed and accuracy.

## Features

- **Two Typing Modes**:
  - **Full Text Mode**: Type the entire provided text to measure overall speed and accuracy.
  - **Timed Mode**: Type as much of the provided text as possible within one minute.
- **Real-time Feedback**: Calculates and displays typing speed (characters per second) and accuracy (%) after completion.
- **Customizable Text**: Easily change the text to be typed in both modes.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A Java IDE or text editor such as Visual Studio Code.

### Installation

1. **Clone the repository**:

    ```sh
    git clone https://github.com/<USERNAME>/typing-speed-test.git
    cd typing-speed-test
    ```

2. **Open the project** in your preferred IDE.

3. **Run the application**:
   - In the IDE, run the `TypingTest` class.

## Usage

- When prompted, choose the typing mode (1 for Full Text Mode, 2 for Timed Mode).
- Start typing the provided text.
- In Full Text Mode, type the entire text and press Enter when done.
- In Timed Mode, type as much of the text as you can within one minute.
- View your typing speed and accuracy after completing the test.

## Example

```java
// Create an instance of TypingTest with sample text and mode
TypingTest test = new TypingTest("The quick brown fox jumps over the lazy dog.", 2);
test.typeInMinute();
