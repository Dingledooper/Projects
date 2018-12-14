from random import choice
from time import sleep


def word_choice():
    # putting database of words into array
    hangman_words = open(r"C:\Users\lyndo\Programming\Python\hangman.txt").read().splitlines()

    # asking to play hangman
    playing = input("Do you want to play hangman? ").lower()
    if playing == "yes":
        print("Great!\n")
    elif playing == "no":
        print("We can play next time.")
        sleep(0.5)
        exit()
    else:
        print("Sorry, I didn't get that.")
        word_choice()

    global word
    word = choice(hangman_words)
    print(f"The word is {len(word)} letters long.")


def main():
    word_choice()

    show_correct = ["_"] * len(word)
    chosen_letters = set([])
    turns = 8

    while '_' in show_correct:
        # guessing words
        user_guess = input('Choose a letter or type "guess" to guess the word: ').lower()

        if user_guess == "guess":
            guessing = input("Guess the word: ")
            if guessing == word:
                print("Congratulations on guessing the correct word!")
                exit()
            else:
                print(f"You guessed wrong! The word was {chosen_word}")

        elif user_guess.isalpha() and len(user_guess) == 1:
            # adding to underscore-filled list if correctly guessed
            if user_guess in word:
                for index, letter in enumerate(word, start=0):
                    if letter == user_guess:
                        show_correct[index] = user_guess
            else:
                turns -= 1
            chosen_letters.add(user_guess)

        else:
            print("Invalid input. Must be a character.\n")
            continue

        # printing result of chosen word
        print(*show_correct, "\tWord Bank:", ", ".join(chosen_letters), "\t", "Guesses left: ", turns, "\n", sep="  ")
        if turns == 0:
            print(f"I beat you! The word was {word}")
    print("Congratulations, you guessed my word!")
    sleep(1)


main()
