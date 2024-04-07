#!/bin/bash

# Prompt user for commit message
echo -n "Enter commit message (press Enter for default): "
read commit_message

# Change to the script's directory
cd "$(dirname "$0")"

# Get the list of modified files
git status --porcelain | while IFS= read -r line; do
   # Extract the file path from the status output
   file=$(echo "$line" | awk '{print $2}')

   # Check if the line is not an untracked file
   if [[ "$line" != "??"* ]]; then
       # Use default commit message if empty, otherwise add a colon to the specified commit message
       message="${commit_message:+$commit_message:}$file"

       # Commit the file with the commit message
       git add "$file"
       git commit -m "$message"
       echo "Committed: $file"
   fi
done
