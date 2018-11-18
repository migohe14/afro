//
//  ViewController.swift
//  Afro
//
//  Created by Enrique Ramos muñoz on 17/11/2018.
//  Copyright © 2018 Enrique Ramos muñoz. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var text: UILabel!
    @IBAction func button(_ sender: Any) {
        text.text = "Clicked Button"
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
    }


}

