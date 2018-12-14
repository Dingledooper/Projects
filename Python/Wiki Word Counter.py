from operator import itemgetter
from re import sub
from requests import get
from bs4 import BeautifulSoup

# word database
with open(r"C:\Users\lyndo\Programming\Python\wordcounter.txt") as word_file:
    english_words = set(texts.strip().lower() for texts in word_file)


def page_words(url):
    word_list = []
    source = get(url).text
    soup = BeautifulSoup(source, features="html.parser")

    # finding words in desired html tags
    for post_text in soup.findAll(('a', 'b', 'i', 'p', "div", "dt", "li", "span", "h1", "h2", "h3", "h4", "h5", "h6")):
        sentences = str(post_text.string)
        all_words = sentences.lower().split()
        for word in all_words:
            word_list.append(word)
    filter_words(word_list)
    print("\nTotal number of words in Wikipedia page: ", len(filtered_list))


def filter_words(word_list):
    global filtered_list
    filtered_list = []

    # filtering strange words using word database
    for word in word_list:
        word = sub(r'([^a-zA-Z\s]+?)', '', word)
        if word in english_words and word not in ["none", "archived", 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                                                  'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                                                  'y', 'z', ""]:
            filtered_list.append(word)
    dictionary(filtered_list)


def dictionary(filtered):
    # sorting words and printing them with their number of appearances
    word_count = {}
    global top_count
    top_count = []
    count = 0

    for word in filtered:
        if word in word_count:
            word_count[word] += 1
        else:
            word_count[word] = 1
    for key, value in sorted(word_count.items(), key=itemgetter(sort_type, ~sort_type)):
        print(key, value)
        count += 1


def main():
    # asking for sort type
    global sort_type
    format_type = input('Enter format for the words ("key", "value"): ')
    if format_type == "key":
        sort_type = 0
    elif format_type == "value":
        sort_type = 1
    else:
        print("That is an invalid input.")
        main()

    page = input("Enter the Wikipedia page you wish the find the word frequency data for: ")
    page_words(page)


main()
