//
//  ContentView.swift
//  smiteHandbookIOS
//
//  Created by Josh Eldridge on 11/21/22.
//

import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        Text(Greeting().greeting())
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
