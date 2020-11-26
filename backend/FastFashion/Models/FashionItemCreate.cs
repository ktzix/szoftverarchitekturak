using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FastFashion.Models
{
    public class FashionItemCreate
    {

       
        public string Type { get; set; }

        public string Detail { get; set; }

        public string Style { get; set; }

        public string Date { get; set; }

        public string pictureUri { get; set; }

        public int UserId { get; set; }

    }
}
