using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Models
{
    public class FashionItem
    {
        
        [Required]
        public string Id { get; set; }

        [Required]
        public string Type { get; set; }

        [Required]
        public string Notes { get; set; }
        
        public DateTime DateOfPurchase
        {
            get { return this.DateOfPurchase;}
            set { new DateTime(System.DateTime.Now.DayOfYear); }
        }
    }
}