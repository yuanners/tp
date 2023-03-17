# User Guide

# Introduction

Thank you for your interest in MoneyGoWhere! This user guide aims to show off the features of the application and how to
use it.

MoneyGoWhere is a point-of-sale, desktop app for tracking sales and inventory, optimised for use via a Command line
Interface (CLI). With the ability to type fast and with familiarity of the system, you will be able to quickly track
sales, check inventory and calculate change. This CLI application is comparable with GUI applications due to its
low-cost and adaptability.

This application is primarily tailored towards the hawker stall owners at The Deck in National University of Singapore.

## Advanced Guide
To see the beginner's version, [please click here](#basic-guide).

### Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Input Formatting](#input-formatting)
4. [Features](#features)
    1. [Items](#items)
        * [Add Items](#add-an-item)
        * [Delete Items](#delete-an-item)
        * [List Items](#list-all-items)
        * [Find Items](#find-an-item)
    2. [Orders](#orders)
        * [Add Order](#add-an-order)
            * [Single Item](#single-item)
            * [Multiple Items](#multiple-items)
        * [List all Orders](#list-all-orders)
    3. [Exit](#exit)
5. [Save File](#save-file)

### Quick Setup

1. Ensure you have Java 11 installed, and [download the latest release of MoneyGoWhere](https://github.com/AY2223S2-CS2113T-T09-2/tp/releases) and place it in a folder.
2. Open the command terminal and navigate to the folder where the `.jar` file is, by doing `cd "<file path>"`.
3. Use the command `java -jar moneygowhere.jar` to launch our application. If correct, you should see the following on startup:
   **TODO: Insert Image**

### Our Style Guide

<br>

### Features

#### Items

##### Add an Item

##### Delete an Item

##### List all Items

##### Find an Item

<br>

#### Orders

##### Add an Order

###### Single Item

###### Multiple Items

##### List all Orders

<br>

#### Exit

<br>

### Save File

<br>

### Command Summary

<br>
<hr>
<br>

## Basic Guide
To see the advanced version, [please click here](#advanced-guide).

### Table of Contents
1. Before installing and using Duke, do note that Java 11 is required. If you do not already have it installed, you can do so [here](https://www.oracle.com/sg/java/technologies/downloads/#java11).
2. After installing Java 11, please download the `.jar` file [here](https://github.com/AY2223S2-CS2113T-T09-2/tp/releases) and place it in a folder. Doing so ensures that the save-file will not be lost, allowing you to use data that you have previously entered. Click on `MoneyGoWhere.jar` to automatically download the file. Ensure that it is the latest version.
   **#TODO: Add image**
3. Move the jar file into a folder. This will allow you to view all data related to MoneyGoWhere easily, as all data files will be stored here.
4. Open the command terminal and navigate to the folder where the `.jar` file is. If you are unsure of how to do so, follow steps 4 to 6.
5. Right-click the file and select the "Copy as path" option.
   #TODO: Add image
6. Open a command terminal by entering `cmd` in your start menu.
7. In the terminal, navigate to the location of the folder by doing `cd "FILE_PATH"`.
    - This step is important as it ensures that the saved tasks file will be saved in the same directory. Otherwise, the file will be stored in the current working directory.
8. Use the command `java -jar ip.jar` to launch MoneyGoWhere. If done correctly, you will see something like this on your first start up:
   **#TODO: Add image**
9. When you are done using the app, enter `bye` to shut the application down. This ensures that Duke will save your data, as saving data is only done during the shut-down process. 