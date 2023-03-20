# User Guide

## General Table of Contents

* [Introduction](#introduction)
* [Advanced Guide](#advanced-guide)
* [Basic Guide](#basic-guide)
* [Statistics](#statistics)
* [Save File](#save-file)
* [Glossary](#glossary)

## Introduction

Thank you for your interest in MoneyGoWhere! This user guide aims to show off the features of the application and how to
use it.

MoneyGoWhere is a point-of-sale, desktop app for tracking sales and inventory, optimised for use via a Command line
Interface (CLI). With the ability to type fast and with familiarity of the system, you will be able to quickly track
sales, check inventory and calculate change. This CLI application is comparable with GUI applications due to its
low-cost and adaptability.

This application is primarily tailored towards the hawker stall owners at The Deck in National University of Singapore.

## Advanced Guide

This version of our guide will bring you through how to use the features of MoneyGoWhere in a single command.

To see the beginner's version, [please click here](#basic-guide). Note that all functionality is the same, regardless of
the mode used.

### Table of Contents (Advanced)

1. [Quick Setup](#quick-setup)
2. [Advanced Mode Style Guide](#advanced-mode-style-guide)
3. [Features](#advanced-commands)
    1. [Help](#advanced-help)
    2. [Items](#advanced-mode-items)
        * [Add Items](#advanced-mode-add-an-item)
        * [Delete Items](#advanced-mode-delete-an-item)
        * [List Items](#advanced-mode-list-all-items)
        * [Find Items](#advanced-mode-find-an-item)
        * [Update Items](#advanced-mode-update-an-item)
    3. [Orders](#advanced-mode-orders)
        * [Add Order](#advanced-mode-add-an-order)
            * [Single Item](#advanced-mode-single-item)
            * [Multiple Items](#advanced-mode-multiple-items)
        * [List all Orders](#advanced-mode-list-all-orders)
        * [Refund an Order](#advanced-mode-refund-an-order)
4. [Save File](#save-file)
5. [Glossary](#glossary)

### Quick Setup

1. Ensure you have Java 11 installed,
   and [download the latest release of MoneyGoWhere](https://github.com/AY2223S2-CS2113T-T09-2/tp/releases) and place it
   in a folder.
2. Open the command terminal and navigate to the folder where the `.jar` file is, by doing `cd "<file path>"`.
3. Use the command `java -jar moneygowhere.jar` to launch our application. If correct, you should see the following on
   startup:
   **TODO: Insert Image**
4. When you are done using MoneyGoWhere, you can exit the application with the command `exit`.

Need more help? [Click here to see our detailed guide!](#setting-up-moneygowhere)

### Advanced Mode Style Guide

* Any text in `this format` are examples of commands.
    * Any words in `<this format>` are parameters that you can fill in and change according to your needs.
* Parameters wrapped in `{}` is optional.
* In the examples, normal text is _italicised_ to signify the options that will be used as the command.

<br>

### Advanced Commands

* Note that all command options have multiple methods of input. For example, the option for name when adding an item can
  be signified by the flag `-n` or `--name`. More details are given in each command.
    * All of these command options are **case-sensitive**.
    * All examples will be given with the short-form flag, as it is the preferred method of input. However, the longer
      version will also be provided.
* All options are signified by `<example>`, which means you can change it according to what you need.
* In all the examples, the use of `>` signifies a user input.
* In advanced mode, all commands are prefixed with `/`, to distinguish them from the basic commands. Without the
  inclusion of this character, commands will be interpreted as a [basic command](#basic-commands).

#### Advanced Help

Entering this command will show you a list of all available commands.

Note that it is possible to use the given index as an command input, but doing so will execute commands as if you were
using the [basic mode](#basic-guide). To use these commands as in advanced mode, append a `/` at the beginning of the
command, along with the proper flags, as explained in the next few sections.

Format: `help`

The output of running the `help` command is as follows:

```text
Please enter a command: 
> help
There are 8 commands you can use in MoneyGoWhere. For more details, please refer to the user guide.
1. additem
2. deleteitem
3. listitem
4. updateitem
5. finditem
6. addorder
7. listorder
8. refundorder
```

#### Advanced Mode Items

There are five different commands you can run for items.

* [Add an Item](#advanced-mode-add-an-item)
* [Delete an Item](#advanced-mode-delete-an-item)
* [List all Items](#advanced-mode-list-all-items)
* [Find an Item](#advanced-mode-find-an-item)
* [Update an Item](#advanced-mode-update-an-item)

##### Advanced Mode Add an Item

This adds a new item to the menu. New items must have a name and price.

Format: `/additem -n "<name>" -p <price>` or `/additem --name "<name>" --price <price>`

* Name refers to the name of the item
    * It is case-sensitive
    * It has an upper limit of 25 characters
    * Names are unique, regardless of how the words are capitalized. `Chicken` and `chicken` will count as the same
      word.
* Price refers to the price of the item
    * It can have up to two decimal points (`2`, `2.1`, `2.22` are all valid inputs).

**Examples of Usage**

* You want to add a new item, _Chicken Rice_, which costs _$3.55_. To do so, use the
  command `/additem -n "Chicken Rice" -p 3.55`.

```text
Please enter a command: 
> /additem -n "Chicken Rice" -p 3.55
The command: /additem was successfully executed!
```

* You want to add a new item, _Egg_, which costs _$0.80_. To do so, use the command `/additem -n "Egg" -p 0.8`,
  or `/additem -n "Egg" -p 0.80`.

```text
Please enter a command: 
> /additem -n "Egg" -p 0.8
The command: /additem was successfully executed!
```

##### Advanced Mode Delete an Item

##### Advanced Mode List all Items

##### Advanced Mode Find an Item

##### Advanced Mode Update an Item

<br>

#### Add an Order

##### Single Item

##### Multiple Items

#### Advanced Mode Orders

##### Advanced Mode Add an Order

###### Advanced Mode Single Item

###### Advanced Mode Multiple Items

##### Advanced Mode List all Orders

<br>

### Advanced Mode Command Summary

<br>
<hr>

## Basic Guide

This version of our guide will bring you through how to use the many features of MoneyGoWhere, in basic mode. In this
mode, you will be prompted step-by-step to the various features
To see the advanced version, [please click here](#advanced-guide). Note that all functionality is the same, regardless
of the mode used.

### Table of Contents (Basic)

1. [Setting up MoneyGoWhere](#setting-up-moneygowhere)
2. [Basic Mode Style Guide](#basic-mode-style-guide)
3. [Basic Commands](#basic-commands)
    1. [Items](#basic-mode-items)
        * [Add Items](#basic-mode-add-an-item)
        * [Delete Items](#basic-mode-delete-an-item)
        * [List all Items](#basic-mode-list-all-items)
        * [Find an Item](#basic-mode-find-an-item)
        * [Update an Item](#basic-mode-update-an-item)
    2. [Orders](#basic-mode-orders)
        * [Add an Order](#basic-mode-add-an-order)
        * [List all Orders](#basic-mode-list-all-orders)
4. [Save File](#save-file)
5. [Glossary](#glossary)

### Setting up MoneyGoWhere

1. Before installing and using MoneyGoWhere, do note that Java 11 is required. If you do not already have it installed,
   you can do so [here](https://www.oracle.com/sg/java/technologies/downloads/#java11).
2. After installing Java 11, please download the `.jar`
   file [here](https://github.com/AY2223S2-CS2113T-T09-2/tp/releases) and place it in a folder. Doing so ensures that
   the save-file will not be lost, allowing you to use data that you have previously entered. Click
   on `MoneyGoWhere.jar` to automatically download the file. Ensure that it is the latest version.
   **#TODO: Add image**
3. Move the jar file into a folder. This will allow you to view all data related to MoneyGoWhere easily, as all data
   files will be stored here.
4. Open the command terminal and navigate to the folder where the `.jar` file is. If you are unsure of how to do so,
   follow steps 5 to 7.
5. Right-click the file and select the "Properties" option. Then, copy the text under "Location".
   ![image](images/userGuide/findFileLocation.png)
6. Open a command terminal by entering `cmd` in your start menu.
7. In the terminal, navigate to the location of the folder by doing `cd "<file path>"`.
    - This step is important as it ensures that the saved tasks file will be saved in the same directory. Otherwise, the
      file will be stored in the current working directory.
8. Use the command `java -jar MoneyGoWhere.jar` to launch MoneyGoWhere. If done correctly, you will see something like
   this on your first start up:
   **#TODO: Add image**
9. When you are done using the app, enter `exit` to shut the application down. This ensures that Duke will save your
   data, as saving data is only done during the shut-down process.

### Basic Mode Style Guide

### Basic Commands

#### Basic Mode Items

##### Basic Mode Add an Item

##### Basic Mode Delete an Item

##### Basic Mode List all Items

##### Basic Mode Find an Item

##### Basic Mode Update an Item

#### Basic Mode Orders

##### Basic Mode Add an Order

##### Basic Mode List all Orders

<br>
<hr>

### Basic Mode Command Summary

## Statistics

## Save File

## Glossary

| Term | Explanation |
|------|-------------|
| Item | Explanation |
