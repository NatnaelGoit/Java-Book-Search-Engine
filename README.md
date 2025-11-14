# Java Book Search Engine

A command line book search tool written in Java that uses an inverted index to search book content and return results ordered by user ratings

## Features

- Builds an inverted index from a directory of plain text book files
- Supports queries with multiple terms and case-insensitive matching
- Returns results ordered by average rating and rating count through the Book comparison logic
- Allows users to add ratings through an interactive command line interface
- Includes JUnit tests for rating logic, average rating, index construction, and term handling

## How it works

Book files are stored in the books directory.
Each file contains a title line, an author line, and the remaining content of the book.

On startup, the search engine:

1. Loads all book files from the directory
2. Creates Book objects implementing the Media interface
3. Builds an inverted index mapping each normalized term to the books that contain it
4. Seeds each book with random ratings so results can be ranked

Users interact through a simple command line interface that allows searching for terms or adding ratings to books.

Results are sorted based on average rating, number of ratings, and title.

## Project Structure

- Media – interface representing media with title, creators, content, and rating-related methods
- Book – implementation of Media; stores tokens, tracks ratings, and defines ordering through Comparable
- SearchClient – main command line program; loads books, builds the inverted index, and processes user commands
- InvertedIndex – helper for constructing the mapping from tokens to the set of books containing them
- QueryCracker – novelty class unrelated to search logic, used to “unlock” results
- Testing – JUnit tests verifying correctness of Book behavior and index creation

## Getting Started

To run the project:

1. Clone the repository
2. Ensure Java is installed
3. Compile the Java files
4. Run the SearchClient class
5. Use the interactive prompt to search or rate books

The program reads all book files, builds the inverted index, and displays search results ordered by rating

## Running Tests

1. The JUnit tests validate:
2. Book object construction
3. Rating count and average rating calculations
4. Correct handling of tokens
5. Correct mapping of terms to books in the inverted index

These tests help ensure the stability and accuracy of the search engine
