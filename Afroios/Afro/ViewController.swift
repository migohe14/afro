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
    
    @IBOutlet weak var signUpPassword: UITextField!
    @IBOutlet weak var signUpEmail: UITextField!
    static var FirebaseIsAlreadyLunched = false
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBAction func logIn(_ sender: Any) {
        logIn()
    }
    @IBAction func signUp(_ sender: Any) {
        signUp()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        if !ViewController.FirebaseIsAlreadyLunched {
            FirebaseApp.configure()
            ViewController.FirebaseIsAlreadyLunched = true
        }
        let tap = UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        tap.cancelsTouchesInView = false
        self.view.addGestureRecognizer(tap)
    }
    private func logIn(){
        self.authIn()
    }
    private func signUp(){
        self.fireSignUp()
    }
    private func authIn(){
        guard let email = email.text else {return}
        guard let password = password.text else {return}
        Auth.auth().signIn(withEmail: email, password: password) { (user, error) in
            if error == nil && user != nil {
                self.MainNavigationController()
            }
            else {
                print("Error")
                
            }
        }
    }
    private func fireSignUp(){
        guard let signUpEmail = signUpEmail.text else {return}
        guard let signUpPassword = signUpPassword.text else {return}
        Auth.auth().createUser(withEmail: signUpEmail, password: signUpPassword) { (authResult, error) in
            if error == nil && authResult != nil {
                self.MainNavigationController()
            }
            guard let user = authResult?.user else { return }
            print(user)
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
    
    private func MainNavigationController(){
        let mainStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
        guard let mainNavigationVC = mainStoryboard.instantiateViewController(withIdentifier: "MainNavigationController") as? MainNavigationController else {return}
        present(mainNavigationVC, animated: true, completion: nil)
    }
    
}
