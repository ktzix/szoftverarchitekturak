using Microsoft.EntityFrameworkCore.Migrations;

namespace FastFashion.Migrations
{
    public partial class useridAsFK2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems");

            migrationBuilder.AlterColumn<int>(
                name: "UserId",
                table: "FashionItems",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems",
                column: "UserId",
                principalTable: "User",
                principalColumn: "UserId",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems");

            migrationBuilder.AlterColumn<int>(
                name: "UserId",
                table: "FashionItems",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddForeignKey(
                name: "FK_FashionItems_User_UserId",
                table: "FashionItems",
                column: "UserId",
                principalTable: "User",
                principalColumn: "UserId",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
