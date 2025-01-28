# ASCII Art Generator

🎨 **Transform images into ASCII art**! This Scala-based application allows you to load images, apply filters, and convert them into ASCII art. Whether you want to print the result to the console or save it to a file, this tool has you covered.

---

## 🚀 Features

- **Image Input**: Load images from your local system or fetch random images from an online API.
- **Filters**:
  - **Scale**: Resize images by factors of 0.25, 1, or 4.
  - **Invert**: Invert the greyscale values of the image.
  - **Brightness**: Adjust the brightness of the image.
- **ASCII Tables**: Use predefined character sets or define your own custom table.
- **Output Options**: Save the ASCII art to a file, print it to the console, or do both.

---

## Requirements

This project uses [sbt](https://www.scala-sbt.org/) and is built with [Scala 3](https://dotty.epfl.ch/).

## 🛠️ Arguments

| Argument                  | Description                                                                     |
|---------------------------|---------------------------------------------------------------------------------|
| **`--image "path"`**      | Load an image from the specified local path (e.g., `../images/test.jpg`).       |
| **`--image-random [seed]`** | Fetch a random image from an online API (optional seed for reproducibility).   |
| **`--scale <factor>`**    | Scale the image by the specified factor (`0.25`, `1`, `4`).                    |
| **`--invert`**            | Invert the greyscale values of the image.                                       |
| **`--brightness <value>`**| Adjust the brightness of the image (e.g., `+10`, `-5`).                        |
| **`--table <1-4>`**       | Use a predefined ASCII table (`1-4`).                                           |
| **`--custom-table "..."`**| Define a custom ASCII character set (e.g., `.:-=+*#%@`).                       |
| **`--output-file "path"`**| Save the ASCII art to the specified file (e.g., `../output/result.txt`).        |
| **`--output-console`**    | Print the ASCII art to the console.                                             |

---

## 🖼️ Examples

1. **Basic Conversion**:
   ```bash
   sbt "run --image ../images/example.jpg --output-console"

2. **Save to file**:
   ```bash
   sbt "run --image "test-image.jpg" --output-file "../outputs/output.txt""

3. **Apply multiple transformations and save to a file and console**: 
    ```bash
    sbt "run --image "test-image.jpg" --rotate +90 --invert --output-file "../outputs/output.txt" --output-console --table "ABCD""
