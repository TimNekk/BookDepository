# BookDepository

CLI app for visiting library :)

## Overview

There are **library** with books and your **bundle** of your books.

You can visit **library** and take some books.

You can also return books to **library**.

You can see all books in **library** and all books in your **bundle**.

## Commands

- Commands are case insensitive.
- You can start command with `/` or without it.
- Arguments are case sensitive.
- Arguments are separated by spaces.
- You should use `"` for arguments that contain spaces.

1. To get list of all books in **library** use command:

```
all
```

2. To get list of all books in your **bundle** use command:

```
list
```

3. To take book from **library** to your **bundle** use command:

```
get title
```

_If there are several books with the same title, you should specify author:_

```
get title author
```

4. To return book from your **bundle** to **library** use command:

```
put title
```

_If there are several books with the same title, you should specify author:_
    
```
put title author
```

5. To exit from app use command:

```
exit
```

_It will return all books from your **bundle** to **library** and exit from app._

## Usage

Install app using Maven.

```
mvn package
```

And run it.

```
java -jar target\BookDepository-0.1.1.jar
```