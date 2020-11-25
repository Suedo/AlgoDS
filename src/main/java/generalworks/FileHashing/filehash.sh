read -p 'Enter the directory path: ' directory
for file in "$directory"/**/*; do
  echo $(md5sum "$file") # wrap $file in "" to prevent whitespaces causing problems
done

# https://stackoverflow.com/a/23408819/2715083
# https://stackoverflow.com/a/20796617/2715083