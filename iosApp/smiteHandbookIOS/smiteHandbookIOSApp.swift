//
//  smiteHandbookIOSApp.swift
//  smiteHandbookIOS
//
//  Created by Josh Eldridge on 11/21/22.
//

import SwiftUI
import shared

@main
struct smiteHandbookIOSApp: App {
    
    init() {
        KoinKt.doInitKoin()
        SmiteRepositoryHelper().sync { error in
            print(error)
        }
        //Greeting().greeting()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
