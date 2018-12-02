//
//  ViewController.swift
//  Afro
//
//  Created by Enrique Ramos muñoz on 19/11/2018.
//  Copyright © 2018 Enrique Ramos muñoz. All rights reserved.
//

import UIKit
import Firebase

class ViewController: UIViewController {
    
    @IBOutlet weak var text: UILabel!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBAction func logIn(_ sender: Any) {
        logIn()
    }
    @IBAction func logOut(_ sender: Any) {
        logOut()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        FirebaseApp.configure()
    }
    private func logIn(){
        text.text = String("Logging In")
        self.authIn()
    }
    private func logOut(){
        text.text = String("Logging Out")
        self.authOut()
    }
    private func authIn(){
        guard let email = email.text else {return}
        guard let password = password.text else {return}
        Auth.auth().signIn(withEmail: email, password: password) { (user, error) in
            if error == nil && user != nil {
                self.text.text = String("Log In")
                MainNavigationController().navigateToGreen()
            }
            else {
                self.text.text = String("Error")
                
            }
        }
    }
    private func authOut(){
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }
    
}
