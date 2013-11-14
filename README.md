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
Start REPL:  
```
M-x cider-jack-in
```
  
Close Emacs:  
```
C-x, C-c
```
