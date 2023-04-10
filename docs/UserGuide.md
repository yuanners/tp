<h1> MoneyGoWhere User Guide </h1>

<div style="width:30%; height:auto; margin-left: auto; margin-right: auto;">

![MoneyGoWhereIcon](docs/images/userGuide/MoneyGoWhere.png)

</div>

<h1> Hello Hawker! </h1>

Thank you for your interest in MoneyGoWhere!

You have just made the first step in making even more money from your business, nice!

This is the user guide for MoneyGoWhere.
**It is designed to help you, a hawker at NUS, learn how to use our system easily**.

The purpose of this guide is to **show easy-to-understand instructions on how to use the program**.

There may be new words in the guide that you have never heard of or seen before, but do not worry! We have made sure all
the harder words are linked to our glossary at the bottom of the guide. All you have to do is click on the difficult
word, and your computer will lead you to what the word means. Easy!

<h1> What is MoneyGoWhere? </h1>

MoneyGoWhere is a **computer program** that **helps you keep track of your sales**.

It works like the _cashier machine_ that you have been using, but is even better! With MoneyGoWhere, **you can see how
much money you make each day** and **what items you sell the most**.

**Now, you can make smarter business decisions, save time, and money**. _Steady_!

<h1><i> "I have problems now, that's why I am reading the guide!" </i></h1>
<h3><i> "But I cannot find what I am looking for, how now?"</i></h3>
We understand that using a new system can be confusing and frustrating. That is why we used simple words and show examples in this guide.

We understand that you may be referencing this guide when business is booming - **you can already barely cope, but you
are unsure of how to use a feature**.

Don't worry! The guide is written in such a way that you can find what you are looking for easily. Click
on [hyperlinks](#glossary) listed in the Table of Contents to jump to the particular section that you want to know more
about.

If you cannot find what you are looking for, you may **call us at 9123 4567** so that we can help you as soon as
possible.


<div style="page-break-after: always;"></div>


<h1> How to use the Guide </h1>

This guide will bring you through:

1. How to use a [Command Line Interface (CLI)](#glossary)
2. How to set up MoneyGoWhere
3. How to use our various [commands](#glossary) to track items, add orders, and generate statistic reports

We will also provide some information on how your data is saved.
<!-- ^^ We can potentially omit this section -->

Before we begin, take note of the following information:
This guide uses **three different colored blocks** and **associated icons** to indicate different things.

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖 This is a story block! Story blocks provide an example on how to use the many features of MoneyGoWhere. 
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 These blocks contain information that you should take note of, or additional details that you might be interested in.
</blockquote>

<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ These are warning blocks. They are used to show you errors that you may encounter.
</blockquote>

# Table of Contents

* [Tutorial on CLI](#Tutorial-on-CLI)
* [Setting up MoneyGoWhere](#Setting-up-MoneyGoWhere)
* [MoneyGoWhere Commands](#MoneyGoWhere-Commands)
* [Style Features](#Style-Features)
* [Summary of Features](#Summary-of-Features)
    * [Help](#Help)
    * [Item Features](#Item-Features)
    * [Order Features](#Order-Features)
    * [Statistics and Report](#Statistics-and-Report)
* [Frequently Asked Questions](#Frequently-Asked-Questions)
* [Glossary](#glossary)

## Tutorial on CLI

If you're already familiar with CLI, that's great! Feel free to move on to the next section about setting up
MoneyGoWhere.

For those who are new to CLI, this section will give you a simple introduction.

So, what is CLI? Well, the device you are using needs to get instructions from you, and you might be familiar with
clicking on icons and images to send these instructions. However, with CLI, you type these instructions.

MoneyGoWhere will prompt you to enter your instructions, such as:

![](https://i.imgur.com/xZ24DTM.png)

[Back to table of contents](#table-of-contents)

## Setting up MoneyGoWhere

1. Before installing and using MoneyGoWhere, do note that Java 11 is required. If you do not already have it installed,
   you can do so [here](https://www.oracle.com/sg/java/technologies/downloads/#java11).
    * For Windows users, download the x64 Installer. Ensure you are on the "Windows" page and download the correct
      version, in the red box.

      ![](https://i.imgur.com/ibRX7fQ.png)

    * For Mac users, download the ARM 64 DMG Installer.Ensure you are on the "macOS" page and download the correct
      version, in the red box.

      ![](https://i.imgur.com/DGn8lrt.png)

1. After installing Java
   11, [please download the latest release of the `MoneyGoWhere.jar` file](https://github.com/AY2223S2-CS2113T-T09-2/tp/releases).
   Click on `MoneyGoWhere.jar` to automatically download the file. Ensure that it is the latest version (the one with
   the biggest number).

   ![](https://i.imgur.com/V8xLkBL.png)

1. Find `MoneyGoWhere` in your Downloads folder.

1. Open `cmd` and navigate to the folder where the `MoneyGoWhere.jar` file is. If you are unsure of how to do so, follow
   steps 5 to 7. Otherwise, you may skip ahead to step 8.

1. Open a command [terminal](#glossary) by entering `cmd` in your start menu and select the first [option](#glossary)
   that appears.
   ![](https://i.imgur.com/Ezt3rky.png)

1. Get the file path of `MoneyGoWhere.jar` in your computer. To easily do so, you can do the following:

    * For Windows users, right-click the file and select the "Properties" [option](#glossary). Then, copy the text
      under "Location".
      ![](https://i.imgur.com/bc72Izn.png)

    * For Mac users, right-click the file and select "Copy".
      ![](https://i.imgur.com/mKfwVrc.png)

1. In the CLI, navigate to the location of the folder by typing `cd `, type or paste the file path that you found from
   the previous step by pressing CTRL and V at the same time on your keyboard, then press enter.

    * For example, if the file path that you have is `C:\Users\natas\Downloads\Folder`, then you should
      enter `cd C:\Users\natas\Downloads\Folder` into the CLI.

    <!--* This step is important as it ensures that the saved tasks file will be saved in the same directory. Otherwise, the file will be stored in the current working directory.-->

1. Use the [command](#glossary) `java -jar MoneyGoWhere.jar` to launch MoneyGoWhere.
   If done correctly, you will see this on your first start up:
   ![](https://i.imgur.com/xZ24DTM.png)

1. When you are done using the app, enter `exit` to shut the application down or simply close the window by clicking on
   the `X` on the top right hand corner of the CLI.

<div style="page-break-after: always;"></div>

[Back to table of contents](#table-of-contents)

## MoneyGoWhere [Commands](#glossary)

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
📖 Meet John! He will be a character that is constantly referenced throughout the next few sections to better explain how the commands work. John is an aspiring hawker, and plans on running a new stall at The Deck in NUS. 
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
💡   For new users, it is recommended to read the sections labelled as <strong>For New Users</strong>. These sections will bring you through using commands in a guided, step-by-step manner.
<br><br>  
As you become more familiar with MoneyGoWhere and want to speed up using the application, we recommend reading sections labelled as  <strong>For Experienced Users</strong>. In these sections, we provide examples on how to use each feature with just a single line of input.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 Even though the <strong>New User</strong> and <strong>Experienced User</strong> commands are entered into the application slightly differently, they both work exactly the same. <br><br> In other words, there is nothing that the <strong>Experienced User</strong> commands can do that the <strong>New User</strong> commands cannot.
</blockquote>

[Back to table of contents](#table-of-contents)

## Style Features

For the following sections, all examples on how to run [commands](#glossary) are shown in images. There are four things
to take note of:

1. Standard text printed by the application is written in **white**.
2. User input is signified and is written in <text  style="color:#88B3F6">**blue**</text>.
3. Success messages and examples of expected output is written in <text  style="color:#7ED321">**green**</text>.
4. Error messages are written in <text style="color:#F25569">**red**</text>.

**When you use the application, the text on your screen will be in white.**

We have chosen to color-code our examples so it is easier for you to follow along and tell apart different parts of the
examples.

Here is an example of what you can expect to see in this Guide.

![](https://i.imgur.com/MT5YgYP.png)

For those reading the **New User** sections, do also note the following:

1. At any point, if you wish to exit from the command, you can do so with `/cancel`

For those reading the **Experienced User** sections, do also note the following:

1. Any words surrounded by `<>`, such as `<price>` are for you to fill in.
2. Options that are surrounded by `{}`, such as `{-n "<name>"}` are optional.
3. All values have to be accompanied by options (begins with `-`, such as `-n` or `--price`). Commands such
   as `/deleteitem delete -i 10` will be an invalid [command](#glossary).

[Back to table of contents](#table-of-contents)

## Summary of Features

There are 10 features built into MoneyGoWhere, as described in the table below.
More details are provided in their individual sections.

| Name                                              | Description                                                          |
|---------------------------------------------------|----------------------------------------------------------------------|
| [Help](#Help)                                     | Displays information about various commands                          |
| [**Item Features**](#Item-Features)               |                                                                      |
| [Add an Item](#Add-an-Item)                       | Adds an item to the menu                                             |
| [Delete an Item](#Delete-an-Item)                 | Deletes an item from the menu                                        |
| [List All Items](#List-All-Items)                 | Lists items in the menu                                              |
| [Update an Item](#Update-an-Item)                 | Updates an item in the menu                                          |
| [Find an Item](#Find-an-Item)                     | Finds an item in the menu, based on its name                         |
| [**Order Features**](#Order-Features)             |                                                                      |
| [Add an Order](#Add-an-Order)                     | Adds an order, with the [index](#glossary) and quantity of each item |
| [List All Orders](#List-All-Orders)               | Lists all orders                                                     |
| [Refund an Order](#Refund-an-Order)               | Refunds an order based on the unique order ID                        |
| **Statistics**                                    |                                                                      |
| [Statistics and Reports](#Statistics-and-Reports) | Generates a report based on various options                          |

[Back to table of contents](#table-of-contents)

## Help

This [command](#glossary) displays information about the other commands available in MoneyGoWhere.

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖 John has just downloaded MoneyGoWhere. Unlike you, he does not know that MoneyGoWhere has a user guide and wants to find out more about the various commands offered by MoneyGoWhere. To find out more about the commands offered, he can use the <code>help</code> command. <br><br>
    Currently, all he knows is that he can do <code>help</code>, or <code>/help</code>.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 The help command does not accept any other input. Entering anything other than <code>help</code> or <code>/help</code> will cause an error.
</blockquote>

<h3> For New Users </h3>
<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖 As someone who is unfamiliar with the application, John wants to be guided step-by-step to use MoneyGoWhere. To view the commands available to him, he can use the <code>help</code> command.
</blockquote>

![](https://i.imgur.com/OlmWorh.png)


<h3> For Experienced Users </h3>
<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖 In the future, John develops a love for typing and now prefers to use just one line to complete an command. He can use the <code>/help</code> command to view how to use these single-line commands.
</blockquote>

![](https://i.imgur.com/FBxnCgC.png)

<h3> Error Messages </h3>

**1. Adding words or letters after the [command](#glossary)**

MoneyGoWhere does not allow for additional letters or words after the `help` command word.

![](https://i.imgur.com/Wki6DmO.png)

**Solution:** Only enter the command word, `help` or `/help`.

[Back to table of contents](#table-of-contents)

## Item Features

Before you start taking orders, you will need to set up your menu. This must be done at least once.

Depending on the size of your menu, you may need to spend quite a bit of time to add all of your menu items into
MoneyGoWhere. It is important that you do this before opening shop for the day, to ensure that your business can be run
without any disruptions.

You may continue to add new items to the menu at any point of time.
<!--This should be a one-time process, but you can always add more items or remove old items at any point.-->

## Table of Contents

* [Add an Item](#Add-an-Item)
* [List all Items](#List-all-Items)
* [Delete an Item](#Delete-an-Item)
* [Update an Item](#Update-an-Item)
* [Find an Item](#Find-an-Item)

## Add an Item

If you need to add an item to the menu, you can use this [command](#glossary). To add an item, you will need the item's
name and price.

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖John wants to run a Chicken Rice store.  Currently, he only has two things to sell and has already set the cost: <strong>White Chicken Rice ($4.50)</strong> and <strong>Egg ($0.80)</strong>. He will need to use the <code>additem</code> command to add these two items.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
💡 There are restrictions for the name and price of items, as shown below:<br><br>

| Option      | Description            | Restrictions                                                                                                                                                                                            |
|-------------|------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Item Name` | The name of the item.  | Any input that is not empty and has less than 25 characters. <br><br> It cannot only have numbers.                                                                                                      |
| `Price`     | The price of the item. | A number, with up to 2 [decimal places](#glossary). For example, `1`, `10.23` and `5.20` are valid inputs. However, `1.234` is not a valid input. <br><br> The price of the item is limited to `10000`. |

</blockquote>


<h3> <strong>For New Users </strong></h3>

This example will show you how to add an item step-by-step.

1. John starts the application for the first time. He sees the greeting and knows that he set up the application
   correctly. Now, he wants to add **White Chicken Rice**, which costs **$4.50** to the menu.

![](https://i.imgur.com/YBkgvEQ.png)

2. He sees that he is successful and feels happy that the application is easy to use. Now, he wants to add **Egg**,
   which costs **$0.80** to the menu.

![](https://i.imgur.com/qeTaDFj.png)

3. Lastly, John uses [`listitem`](#List-all-Items) to check that he has added the items correctly.
   **//TODO: HYPERLINK listitem**.

![](https://i.imgur.com/yiScNE0.png)


<hr style="width:60%;margin:25px auto;"/>

<h3> <strong>For Experienced Users </strong></h3>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 To use the command in this manner, remember to add a <code>/</code> before the command, such as <code>/additem</code>. 
</blockquote>

**[Command](#glossary) Format**
These are the different command formats that are accepted when adding a new item into the menu.

```text
/additem --name "<item name>" --price <price>
```

```text
/additem -n "<item name>" -p <price>
```

You may also interchange the location of the [flags](#glossary) in the commands.

For example, the following command is also valid.

```text
/additem  -p <price> -n "<item name>"
```

This example below will show you how to add an item in a single command.

1. John starts the application for the first time. He sees the greeting and knows that he set up the application
   correctly. Now, he wants to add **White Chicken Rice**, which costs **$4.50** to the menu.

![](https://i.imgur.com/iblVSBb.png)

2. He sees that he is successful and feels happy that the application is easy to use. Now, he wants to add **Egg**,
   which costs **$0.80** to the menu.

![](https://i.imgur.com/ARJOgRB.png)

3. Lastly, John uses [`listitem`](#List-all-Items) to check that he has added the items correctly.

![](https://i.imgur.com/pYNA0uT.png)

<h3>Error Messages</h3>

<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ The next few examples are invalid inputs, designed to show off some error messages we have in place. This is not the full list of error messages. Additionally, these error messages will be shown for both <strong>New User</strong> and <strong>Experienced User</strong> commands if the mistake is made. 
</blockquote>

**1. Name already exists**

MoneyGoWhere does not allow for multiple items with the same name. If you try to put in an item with the same name, you
will get the following error.

![](https://i.imgur.com/ULSYB6F.png)

**Solution:** Use a different name.

<br> 

**2. Price has more than two [decimal places](#glossary)**

MoneyGoWhere does not allow you to enter a number with more than two [decimal places](#glossary). This is because there
are no such denominations in real life.

![](https://i.imgur.com/bGhE59B.png)

**Solution:** Limit the price to 2 decimal points.

<br> 

[Back to table of contents](#table-of-contents)

## List all Items

To see all the items you have entered in your menu, use this [command](#glossary)!

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖  John has spent the past 10 minutes entering all the items he plans to sell. Happy with his work, he wants to look at all the items in the menu to make sure that everything has been enetered correctly. To view the items in his menu, he will need the <code>listitem</code> command.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 This command uses only one word. Adding anything else after the command will cause MoneyGoWhere to not recognize the command. 
</blockquote>

1. John has added 10 items to the menu and he wants to check that they are all correct. He uses the command `listitem`
   or `/listitem` to do so.

   ![](https://i.imgur.com/4TZqeA7.png)

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 Notice typos? Don't worry, it's intentional! These typos will be used in later sections. 
</blockquote>


<h3>Error Messages</h3>

<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ The next example is an invalid input, designed to show off the error messages we have in place. This is not the full list of error messages. Additionally, these error messages will be shown for both <strong>New User</strong> and <strong>Experienced User</strong> commands if the mistake is made. 
</blockquote>

**1. Adding words or letters after the [command](#glossary).**

Adding anything after `listitem` or `/listitem` will cause the command to be invalid.

![](https://i.imgur.com/oPwXAGW.png)

<br>

[Back to table of contents](#table-of-contents)

## Delete an Item

If you decide to stop selling a particular item, you can remove it from the menu with this [command](#glossary).

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">💡By using this command, you may potentially change the other index numbers of your other menu items. This may or may not affect the way you add items to orders. It is recommended that you use <code>listitem</code> to confirm if there are any changes.</blockquote>

To view more about listitem, [click here](#list-all-items).

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
📖  As per the previous example, John currently plans to sell Fried Rice. However, after asking his friends, he realized that no one wants to buy Fried Rice from a Chicken Rice stall. The menu currently looks like this: <br><br>

![](https://i.imgur.com/V30OtYU.png)

So, he has decided to delete Fried Rice, which has an index of <strong>8</strong> from his menu using the <code>
deleteitem</code> command.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
💡 There are restrictions for the index, as shown below:<br><br>

| Option  | Description                           | Restrictions                                                                                                             |
|---------|---------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| `Index` | The position of the item in the menu. | The entered number must be a valid index number from `listitem`. <br><br>The entered number cannot be a negative number. |

</blockquote>

<h3> For New Users </h3>

1. John wants to delete **Fried Rice**, which has index number **8**.

![](https://i.imgur.com/M2twWZL.png)


<hr style="width:60%;margin:25px auto;"/>

<h3> For Experienced Users </h3>

**[Command](#glossary) Format**

```text
/deleteitem --index <index>
```

```text
/deleteitem -i <index>
```

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 To use the command in this manner, remember to add a <code>/</code> before the command, such as <code>/deleteitem</code>. 
</blockquote>

1. John wants to delete **Fried Rice**, which has index number **8**.

![](https://i.imgur.com/Gagm3P2.png)

<h3>Error Messages </h3>
<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ The next example is an invalid input, designed to show off the error messages we have in place. This is not the full list of error messages. Additionally, these error messages will be shown for both <strong>New User</strong> and <strong>Experienced User</strong> commands if the mistake is made. 
</blockquote>

**1. Invalid index**

You will not be able to delete an item if the index number of that item does not exist in the menu.
In other words, you cannot remove something that does not exist!

![](https://i.imgur.com/dXH4yq4.png)

**Solution:** Use a valid index.

<br>

[Back to table of contents](#table-of-contents)

## Update an Item

Realised you entered the wrong name or price? You can update your items with this [command](#Glossary)!

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
📖 John has been happily using MoneyGoWhere to input all his items. However, he is a bit clumsy and entered a few wrong things. This is what the menu currently looks like: <br><br>

![](https://i.imgur.com/6Q3hSNz.png)

He wants to change two things. First, the word <strong>"Vegebatles"</strong>  at index number 6 is spelt wrongly. Also, he wants to <strong>increase the price of "Vegetables" to $2.00</strong>. </br></br> Next, the item <strong>"Curry Chicken Rice" should cost $5.50</strong>. He can update these items with the <code>updateitem</code> command.
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
💡 There are restrictions for the index, name, and price of items, as shown below:<br><br>

| Option  | Description               | Restrictions                                                                                |
| ------- | ------------------------- | ---------------------------------------------------------------------------------------------- |
| `Index` | The index number of the item.     | Must be a valid index from `listitem`, cannot be a negative number.</br></br> This option is compulsory. |
| `Name`  | The new name of the item.  | It cannot already exist in the menu.</br></br> This option is not compulsory.                            |
| `Price` | The new price of the item. | It can have at most two decimal places. </br></br>This option is not compulsory.                         |

</blockquote>

<h3> For New Users </h3>

1. John wants to **change spelling of the word "Vegebatle" to "Vegetable"**. To do so, he first enters the [command](#Glossary) `updateitem`, then confirms that he wants to change the name. He puts in the correct spelling, **without quotation marks**, and then indicates he does not want to change the price.

![](https://i.imgur.com/z09QX60.png)

2. John wants to **change the price of "Curry Chicken Rice" to $5.50, instead of $55**. To do so, he first enters the command, indicates he does not want to change the name, then confirms he wants to change the price. He then puts in the correct price.

![](https://i.imgur.com/esb1Agx.png)

3. After updating, John checks to make sure that the items are now correct with `listitem`.

![](https://i.imgur.com/w4JXXNl.png)

<hr style="width:60%;margin:25px auto;"/>

<h3> For Experienced Users </h3>

**[Command](#Glossary) Format**
```text
/updateitem --index <index> {--name "<name>"} {--price <price>}
```
```text
/additem -i <index> {-n "<name>"} {-p <price>}
```

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 To use the command in this manner, remember to add a <code>/</code> before the command, such as <code>/updateitem</code>. 
</blockquote>

1. John wants to change the name "Vegebatle" to **"Vegetable"**. The item is currently at index **6**.

![](https://i.imgur.com/k859ijM.png)

2. Next, John wants to change the price of "Curry Chicken Rice", at index 8, to **$5.50** instead of $55.

![](https://i.imgur.com/4qA5esD.png)

3. After updating, John checks to make sure that the items are now correct with `/listitem`.

![](https://i.imgur.com/2gHDVK3.png)


<h3> Error Messages </h3>
<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ The next example is an invalid input, designed to show off the error messages we have in place. This is not the full list of error messages. Additionally, these error messages will be shown for both <strong>New User</strong> and <strong>Experienced User</strong> commands if the mistake is made. 
</blockquote>

**1. Missing Index**

Without indicating the index, MoneyGoWhere will not know what item you want to update.

![](https://i.imgur.com/ou1bDzX.png)

**Solution:** Include the index.

<br>

[Back to table of contents](#Table-of-Contents)

## Find an Item

If you forget the index of an item, or want to see all items with a specific word in the name, you can use this [command](#Glossary).

<blockquote style="background-color:#EAF5FF; color:#364253; border-color:#3399FF; padding: 2% 3%">
    📖  It's a new day and John wants to continue exploring MoneyGoWhere. However, his memory isn't the best, and he cannot remember how many different items he has with the word "Chicken" in the name. So, he wants to find all of these items, with the <code>finditem</code> command. 
</blockquote>

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
💡 There are restrictions for keyword, as shown below:<br><br>

| Option    | Description                               | Restrictions  |
| --------- | ----------------------------------------- | ---------------- |
| `Keyword` | The name or part of the item name you are searching for. | The keyword cannot be empty. |

</blockquote>

<h3> For New Users </h3>

1. John enters the command, and then enters "chicken" to search for all items that have "chicken" in the name, regardless of whether the item name contains capital letters.

![](https://i.imgur.com/0F1hPZK.png)


<hr style="width:60%;margin:25px auto;"/>

<h3> For Experienced Users </h3>

**[Command](#Glossary) Format**
```text
/finditem "<keyword>"
```

<blockquote style="background-color:#FEEFD0; color:#364253; border-color:#877039; padding: 2% 3%">
    💡 To use the command in this manner, remember to add a <code>/</code> before the command, such as <code>/finditem</code>. 
</blockquote>

1. John enters the command, and then enters "chicken" to search for all items that have "chicken" in the name, regardless of capitalization.

![](https://i.imgur.com/g18iIZL.png)


<h3> Error Messages </h3>

<blockquote style="background-color:#FADDDD; color:#364253; border-color:#893232; padding: 2% 3%">
    ❗ The next example is an invalid input, designed to show off the error messages we have in place. This is not the full list of error messages. Additionally, these error messages will be shown for both <strong>New User</strong> and <strong>Experienced User</strong> commands if the mistake is made. 
</blockquote>

**1. Not entering a keyword**

Without a keyword, MoneyGoWhere will not know what to look for your menu.

![](https://i.imgur.com/0q8kQFd.png)

**Solution:** Include the word or letters you want to look for.

<br>

[Back to table of contents](#Table-of-Contents)

