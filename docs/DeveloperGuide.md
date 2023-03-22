# Developer Guide

## Table of Contents
* [Acknowledgements](#acknowledgements)
* [Introduction](#introduction)
* [Design](#design)
* [Implementation](#implementation)
* [Requirements](#requirements)
  * [Project Scope](#project-scope)
  * [User Stories](#user-stories)
  * [Non-functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)

## Acknowledgements

## Introduction
The aim of this guide is to provide a comprehensive explanation of the internal mechanisms of MoneyGoWhere. 
This will enable upcoming software engineers to acquire a detailed understanding of the application's implementation, making it easier for them to work on the project.

## Design

## Implementation



### Item Commands
#### Overview
* [Add an Item](#add-an-item)
* [Delete an Item](#delete-an-item)
* [List all Items](#list-all-items)
* [Update an Item](#update-an-item)
* [Find an Item](#find-an-item)

#### Add an Item
##### Advanced
This sequence diagram models the interaction between various components in MoneyGoWhere when the user inputs the command `/additem`.

The general workflow of `/additem` is as follows:
1. User input is passed to `MoneyGoWhere`.
1. `MoneyGoWhere` then creates a new `Command` object using the user input, whose constructor invokes `Parser#formatArguments` method to extract the arguments for each flag into a `Map`.
1. `Router#handleRoute` is then invoked to process the command and calls `Router#proRoute` which invokes `Menu#addItem` method to run the `/additem` command.
1. Once the command runs, `Menu#addItem` invokes `AddItemValidation#validateFlags` to check if all the required flags have been given.
   * If there are missing flags, a message indicating that the usage is invalid will be printed using `Ui#println` and control is given back to `MoneyGoWhere`.
1. `Menu#addItem` then invokes `AddItemValidation#validateCommand` which in turn, calls all of the following validation method to check the arguments provided.
   * `AddItemValidation#validateArgument` checks if the user input `String` is empty
     * If the user input is empty, a message indicating that the input is empty is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
   * `AddItemValidation#validateName` checks if the given name is empty or exceeds the limit of 25 characters
     * If the name violates these naming constraints, a message indicating that the name is too short or too long is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
   * `AddItemValidation#validateDuplicateName` checks if the given name already exists in the `ArrayList<Item> items` of `Menu`.
     * If the name already exists, a message indicating that the item name already exists is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
   * `AddItemValidation#validatePrice` checks if the given price is empty, is not a number, is negative or has more than 2 decimal points
     * If any of the above is true, a message indicating the constraint that it has violated is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
1. A new `Item` object is then created using the name and price given
1. `Menu#appendItem` is invoked on the new `Item` object to add it to `ArrayList<Item> items` in `Menu`.
1. The, `Menu#save` is invoked to save the changes to the local storage file.
1. `Router` object then calls `Ui#printCommandSuccess` to print a message indicating that the item has been successfully added to the menu.

##### Basic
This sequence diagram models the interaction between various components in MoneyGoWhere when the user inputs the command `additem`.

The workflow is the same if the user input is `1` or `1.`.

The general workflow of `additem` is as follows:
1. `MoneyGoWhere` then creates a new `Command` object using the user input, whose constructor invokes `Parser#formatArguments` method to extract the arguments for each flag into a `Map`.
1. `Router#handleRoute` is then invoked to process the command and calls `Router#assistRoute` which invokes `MenuAssistant#addItem` method to run the `additem` command.
1. Once the command runs, it can be aborted at any time when the user inputs `/cancel`.
1. `MenuAssistant#addItem` invokes `MenuAssistant#getName` to get the name of the item to be added.
1. `MenuAssistant#getName` gets the name from the user and invokes `AddItemValidation#validateName` to check if the given name is empty or exceeds the limit of 25 characters and `AddItemValidation#validateDuplicateName` to check if the given name already exists in the `ArrayList<Item> items` of `Menu`
   * If the name violates these naming constraints, a message indicating that the name is too short or too long is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
   * If the name already exists, a message indicating that the item name already exists is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
1. `MenuAssistant#addItem` then invokes `MenuAssistant#getPrice` to get the price of the item to be added.
1. `MenuAssistant#getPrice` gets the name from the user and invokes `AddItemValidation#validatePrice` to check if the given price is empty, is not a number, is negative or has more than 2 decimal points
   * If any of the above is true, a message indicating the constraint that it has violated is printed using `Ui#println` and control is given back to `MoneyGoWhere`.
1. A new `Item` object is then created using the name and price given
1. `Menu#appendItem` is invoked on the new `Item` object to add it to `ArrayList<Item> items` in `Menu`.
1. The, `Menu#save` is invoked to save the changes to the local storage file.
1. `Router` object then calls `Ui#printCommandSuccess` to print a message indicating that if the item has been successfully added to the menu or if the user has cancelled the command accordingly.


## Requirements

### Project Scope

### User Stories
| Version | As a...            | I want to...                         | So that I can...                                                                 |
| ------- | ------------------ | ------------------------------------ | -------------------------------------------------------------------------------- |
| v1.0    | Hawker Store Owner | Add an item                          | Add it to an order in the future                                                 |
| v1.0    | Hawker Store Owner | Delete an item                       | Remove it from the menu                                                          |
| v1.0    | Hawker Store Owner | List all items                       | View all items in the menu, including its index and price                        |
| v1.0    | Hawker Store Owner | Add an item to an order              | Track what customers order and calculate its total cost                          |
| v1.0    | Hawker Store Owner | Add multiple items to an order       | Save time by combining multiple items from a single customer into one order      |

### Non-functional Requirements
1. The application should be able to run on any operating system with `Java 11` installed.
2. The application should be responsive.
3. The application should be simple enough for a novice who is not familiar with a Command Line Interface (CLI) to use.
4. The application should be easily adaptable to people who are already well-versed in using a traditional Point-of-Sale (POS) system.

### Glossary
