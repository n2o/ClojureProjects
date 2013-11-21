Emacs Cheatsheet
================
Configuring Emacs to work with a german keyboard layout on a Mac
----------------------------------------------------------------
**Use CMD as the META button**  
Copy this snippet into ```~/.emacs.d/init.el``` somewhere into the existing file.
```
(setq mac-command-modifier 'meta
        mac-option-modifier 'none
        default-input-method "MacOSX")
```

Useful Emacs commands
---------------------
Start REPL:  ```M-x cider-jack-in```
  
Sometimes the file needs to be loaded by hand into the REPL: ```C-c C-k```
  
Put your cursor at the end of the new function and do:  ```C-x C-e```
  
Save the current buffer to its file (save-buffer): ```C-x C-s```
  
Cut (really kill): ```C-w```
  
Copy (really kill-ring-save): ```M-w```
  
Paste (really yank): ```C-y```
  
Split the selected window into two windows, one above the other (split-window-vertically): ```C-x 2```
  
Split the selected window into two windows positioned side by side (split-window-horizontally): ```C-x 3```
  
Jump between open windows: ```C-x C-b```
  
Close Emacs:  ```C-x C-c```
