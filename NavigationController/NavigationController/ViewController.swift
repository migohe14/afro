//
//  ViewController.swift
//  NavigationController
//
//  Created by Enrique Ramos muñoz on 02/12/2018.
//  Copyright © 2018 Enrique Ramos muñoz. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    @IBOutlet weak var label: UILabel!
    
    @IBAction func button(_ sender: Any) {
        label.text="something happen";
        print("something happen");
        NavigateToPageTwo();
    }
    private func NavigateToPageTwo(){
        let mainStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
        guard let mainNavigationVC = mainStoryboard.instantiateViewController(withIdentifier: "MainNavigationController") as? MainNavigationController else {return}
        present(mainNavigationVC, animated: true, completion: nil)
    }
}

