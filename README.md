On Cloning this repositry please follow the following;
Open the project in their IDE (VS Code, IntelliJ, etc.).

Let Maven download the UCanAccess library (or use your manual JARs if you’re not using Maven).

Run Main.java and have the app connect to the bankdb.accdb file exactly as you do now.

That means we need to store everything that’s needed, but not the junk (.class files, IDE temp files, etc.).

🛠️ Step 1 – Create a .gitignore File
A .gitignore tells Git which files not to track. It keeps your repo clean.
Create a new file in your project root (next to pom.xml) called .gitignore (no extension, just the name).
Paste this inside:

gitignore
# Ignore compiled Java classes
*.class
bin/
target/

# Ignore IDE-specific files (VS Code, Eclipse, IntelliJ)
.vscode/
.idea/
*.iml
.project
.classpath
.settings/

# Ignore macOS stuff
.DS_Store

# Ignore log files
*.log

# Ignore any temporary files
*.tmp
If you already have a .vscode folder with some settings you want to share (like launch configurations), you can remove .vscode/ from the list. But for a beginner, it's safer to let each user have their own IDE settings.

📂 Step 2 – Decide What to Include (and Where)
Your project root should look like this before you commit:

text
your-project-folder/
├── src/                     (all your .java files)
├── pom.xml                  (if using Maven)
├── .gitignore
├── bankdb.accdb             (the Access database file)
└── (maybe a README.md)
That’s it.
If you extracted UCanAccess JARs manually and put them in a lib/ folder, also add that lib/ folder and make sure your classpath points there.

But if you’re using Maven (with pom.xml), you don't need to store the library files – Maven will download them for each user. That’s cleaner.

🗃️ Step 3 – The Database File (bankdb.accdb)
Yes, you must commit the database file because your app expects to find it in the project folder (./bankdb.accdb).

When you commit the .accdb:

Make sure it’s not open in Microsoft Access, or it may be locked or corrupted during commit.

It’s a binary file – Git handles it fine, but if it ever gets large (hundreds of megabytes), you might want to use Git LFS. For a small bank app, just commit it normally.

Important: The database file you commit should be empty (just the table structure created by DatabaseManager.initializeDatabase()). That way, when someone else runs the app, it will create the table on first launch. If you already have real customer data in it, delete those rows before committing (to avoid sharing personal info). A blank database is perfect.

🚀 Step 4 – Initialize Git and Push
Open a terminal in your project folder and:

bash
# 1. Initialize a Git repository
git init

# 2. Stage ALL the files (Git will respect .gitignore)
git add .

# 3. Commit
git commit -m "First commit - Java bank app with Access DB"

# 4. Create a new repository on GitHub (or GitLab) – don't add README or .gitignore there, keep it empty.
# Then link and push:
git remote add origin https://github.com/yourusername/your-repo-name.git
git branch -M main
git push -u origin main
After that, your whole project is online.

👥 Step 5 – What Does Someone Else Do to Get It Working?
Write a small README.md in the root folder with these instructions (so you remember, and others know):

markdown
# First Bank Uganda – Account Opening App

## How to run
1. **Clone this repo**  
   `git clone https://github.com/yourusername/your-repo-name.git`

2. **Open the project in your IDE**  
   - In VS Code: open the folder, accept any Java prompts.
   - In IntelliJ/Eclipse: import as Maven project (the IDE will use `pom.xml`).

3. **Let Maven download dependencies**  
   - If you use Maven, right-click on `pom.xml` → "Maven → Reload project".  
   - If you use manual JARs, make sure your IDE's build path includes the `lib/` folder.

4. **Run the application**  
   - Find `Main.java` and run it.  
   - The app will create the `Customers` table in `bankdb.accdb` automatically.

5. **Enjoy!**
