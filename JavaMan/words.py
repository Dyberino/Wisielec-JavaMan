f = open("JavaMan/WORDS.txt", mode="r", encoding="UTF-8")
f2 = open("JavaMan/WORDS2.txt", mode="w", encoding="UTF-8")
for line in f:
    print(line)
    if(len(line)<=16):
        f2.write(line)