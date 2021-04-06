# BMO Data Exporter

This simple command-line application scrapes BMO 
transaction history data (e.g. time, detail, amount) from downloaded BMO online banking 
HTML file, and saves the data in terms of CSV file.

The aim for this project is to produce a user-friendly app,
such that non-geek users can also use it easily. Currently,
it is only a prototype command line application that do not suit non-geek use.

## How to use:

- Sign in to [BMO online banking](https://www1.bmo.com)
- Enter the page that contains your transaction history
  (by right clicking on desired account name e.g. saving)
  
- Right click and select "Save Page As"
- Save the page in HTML.
- Run the Java application and enter the path to HTML.

## To-do

- User Friendly GUI
- Browser Extension that does the following
    - Only has one button named Save
    - Upon clicking save, the csv file will be automatically opened in excel
      (or equivalents such as libreoffice).
      
    - Users should not be confused at all, even 
      if the user is not familar with computer.
      
- Proper Unit Test
    - Difficulty being, using someone's transaction history
    as unit test would reveal sensitive information. Need to implement
      unit test with encrypted file.
      