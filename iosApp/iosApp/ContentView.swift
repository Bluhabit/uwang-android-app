import SwiftUI
import shared

struct ContentView: View {

    @ObservedObject private(set) var viewModel:ViewModel
    
    @State private var noteName:String = ""
    
    init(viewModel: ViewModel) {
        self.viewModel = viewModel

    }
	var body: some View {
    
                VStack(alignment:.leading,spacing: 10.0){
                    HStack(spacing: 8){
                        TextField("Tulis Note", text: $noteName)
                        Spacer()
                        Button(action: {
                            viewModel.saveNewNote(noteName: noteName)
                            noteName = ""
                        }, label: {
                            Text("Add")
                        })
                    }.padding(
                        EdgeInsets.init(top: 0, leading: 8, bottom: 0, trailing: 8)
                    )
                    List(viewModel.notes, id: \.self,rowContent: { n in
                        VStack(alignment: .leading){
                            HStack(alignment: .center){
                                VStack(alignment: .leading){
                                    Text("\(n.noteName)")
                                    Text("\(n.noteDescription)")
                                }
                                Spacer()
                                Button(
                                    action: {
                                        viewModel.deleteNoteById(noteId: n.noteId)
                                    },
                                    label: {
                                        Text("Delete")
                                    }
                                )
                            }
                        }
                    })
                }
        
	}
}

extension ContentView{
    class ViewModel: ObservableObject{
        let sdk:NoteSDK
        
        @Published var notes:[NoteModel] = [NoteModel]()
        
        init(sdk:NoteSDK){
            self.sdk = sdk
        }
        
        func getAllNotes(){
            self.sdk.getListAllNote(
                completionHandler: {
                    note,error in
                    if(note != nil){
                        self.notes = note!
                    }else{
                        self.notes = []
                    }
                }
            )
        }
        
        func saveNewNote(noteName:String){
            let data = NoteModel(
                noteId: UUID().uuidString,
                noteName: noteName,
                noteDescription: "Deskripsi dari \(noteName)"
            )
            self.sdk.insertNewNote(data:data ,completionHandler: {
                _,err in
                
                self.notes.append(data)
            })
        }
        
        func deleteNoteById(noteId:String){
            self.sdk.deleteNoteById(noteId: noteId,completionHandler:{
                _,err in
                
                self.getAllNotes()
            })
            
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: .init(sdk: CreateIOSSDK().createNoteSDK()))
	}
}
