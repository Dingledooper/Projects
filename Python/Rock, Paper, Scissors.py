from random import choice
from sys import exit
from time import sleep

# user choices
rps = {"rock": 1, "paper": 2, "scissors": 3}


def start():
    play = input("Do you want to play Rock, Paper, Scissors?\n").lower()
    while True:
        if play == "yes":
            global max_points
            max_points = input("\nBest out of: ")
            if not max_points.isdigit():
                print("That is an invalid choice.")
                continue
            print('Enter "rock", "paper", "scissors". Enter "quit" to quit the game.')
            break
            pass
        elif play == "no":
            print('We can play next time.')
            sleep(0.5)
            exit()
        else:
            print("Sorry, I didn't get that")
            start()


start()


def main():
    # user and computer score count
    user_score = 0
    comp_score = 0

    # while loop for re-looping and continue functionality
    while user_score + comp_score < int(max_points):
        user_choice = input('Enter your choice: ').lower()
        comp_choice = choice(["rock", "paper", "scissors"])

        if user_choice == "quit":
            exit()
        elif user_choice not in rps:
            print("That is an invalid choice.")
            continue
        else:
            user_num = rps.get(user_choice)
            comp_num = rps.get(comp_choice)
            diff = comp_num - user_num
            print("Computer's choice:", comp_choice)

        # win based on numbers
        if diff in [-1, 2]:
            user_score += 1
            print("You score a point\n")
        elif diff in [1, -2]:
            comp_score += 1
            print("Computer scores a point\n")
        else:
            print("It's a tie\n")

    print("Your final score: ", user_score)
    print("Computer's final score: ", comp_score)

    if user_score > comp_score:
        print("Congratulations, you win!")
    else:
        print("Computer won! Better luck next time.")
    sleep(1)

main()
