function transformerEnQuestion(){
    if(tableauMots.length > 0){
        tableauMots[0] = tableauMots[0].toLowerCase();
         if(voyelles.includes(tableauMots[0][0])){
            tableauMots.unshift("Est-ce qu'");
         } else {
            tableauMots.unshift("Est-ce que");
         }
         tableauMots.push("?");
    }


}